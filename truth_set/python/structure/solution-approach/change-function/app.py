from typing import List, Dict, Optional, Union
from db import Database

class User:
    def __init__(self, id: int, name: str, email: str):
        self.id = id
        self.name = name
        self.email = email
db = Database()
 def get_all_users() -> Dict[str, Any]:
    """
    Fetches all users from the database.
    """
    result = db.execute_query('SELECT id, name, email FROM users;')
    return result
def add_user(name: str, email: str) -> Dict[str, Optional[Union[List, str]]]:
    """
    Adds a new user to the database.
    """
    result = db.execute_query('INSERT INTO users (name, email) VALUES (%s, %s);', [name, email])
    return result

def add_user_1(name: str, email: str) -> Dict[str, Optional[Union[List, str]]]:
    """
    Adds a new user to the database.
    """
    result = db.execute_query('INSERT INTO users (name, email) VALUES (%s, %s);', [name, email])
    return result

if __name__ == "__main__":
    users_old = get_all_users()
    if users_old["success"]:
        print('Users (Old):', users_old["data"])
    else:
        print('Error fetching users (Old):', users_old["error"])
    add_user_result_old = add_user('John Doe', 'john.doe@example.com')
    if add_user_result_old["success"]:
        print('User added successfully (Old).')
    else:
        print('Error adding user (Old):', add_user_result_old["error"])
