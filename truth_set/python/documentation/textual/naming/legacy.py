 def proc(u):
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
