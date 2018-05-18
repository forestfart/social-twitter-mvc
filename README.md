# Spring REST twitter
CRUD application for twitting and following other twitters

[![Build Status](https://travis-ci.org/forestfart/social-twitter-rest.svg?branch=master)](https://travis-ci.org/forestfart/social-twitter-rest)
[![codecov](https://codecov.io/gh/forestfart/social-twitter-rest/branch/master/graph/badge.svg)](https://codecov.io/gh/forestfart/social-twitter-rest)

This app has been deployed at 
```bash 
base_url = http://social-twitter-rest.herokuapp.com/twitter
```

## API documentation

### Create user
Users are being created when first tweet is created in HTML path (see 'login' in Posting below)

### Posting
Allowing post a 140 character message.

HTTP method: 
```bash 
POST base_url/'login'
```
required JSON body data parameter: 
```bash 
{
    "content": "Sample content"
}
```

### Wall
User can be see a list of the messages they've posted
HTTP method: 
```bash
GET base_url/'login'
```

### Following
User can follow another user
HTTP method: 
```bash
GET base_url/'login'/'followLogin"
```

### Timeline
List of the messages posted by all the users they follow
```bash
HTTP method: GET base_url/'login'/timeline
```

Copyright (c) 2018 Michal Michalik