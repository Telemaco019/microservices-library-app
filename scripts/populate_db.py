import json
import logging

import requests

ENGINE_URL = 'http://localhost:8081'
LOGGING_LEVEL = logging.INFO


def read_json(file_name):
    with open(file_name) as file:
        return json.load(file)


class Author:
    def __init__(self, id, name):
        self.id = id
        self.name = name


def make_add_author_call(**kwargs):
    log.info(f'Adding author: {kwargs}')
    resp = requests.post(ENGINE_URL + '/authors', json=kwargs)
    if resp.status_code == 200:
        resp_json_content = resp.json()
        return Author(resp_json_content['id'], resp_json_content['name'])
    else:
        raise Exception(f'Error adding author to DB: {resp.content}')


def make_add_book_call(**kwargs):
    log.info(f'Adding book: {kwargs}')
    resp = requests.post(ENGINE_URL + '/books', json=kwargs)
    if resp.status_code != 200:
        raise Exception(f'Error adding book to DB: {resp.content}')


def main():
    book_json_list = read_json('books.json')
    created_authors_dict = {}

    for book in book_json_list:
        try:
            if created_authors_dict.get(book['author']) is None:
                author = make_add_author_call(authorName=book['author'])
                created_authors_dict[author.name] = author
            else:
                author = created_authors_dict[book['author']]
            make_add_book_call(
                title=book['title'],
                authors=[author.id],
                pages=book['pages'],
                publicationDate=f'{book["year"]}-01-01'
            )
        except Exception as e:
            log.error(e)


if __name__ == '__main__':
    logging.basicConfig(format='[%(levelname)s %(asctime)s] - %(message)s', level=LOGGING_LEVEL)
    log = logging.getLogger(__name__)
    main()
