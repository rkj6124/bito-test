def proc(u):
    if not u:
        raise ValueError("Input dictionary cannot be empty")
    if 'nm' not in u:
        raise ValueError("Invalid input")
    u['a'] = u['nm'].upper()
    return u

user_data = {
    'nm': 'john doe',  
    'a': '' 
}

result = proc(user_data)
print(result)
