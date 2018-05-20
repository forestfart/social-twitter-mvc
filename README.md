# Spring REST twitter
CRUD application for tweaps

[![Build Status](https://travis-ci.org/forestfart/social-twitter-rest.svg?branch=master)](https://travis-ci.org/forestfart/social-twitter-rest)
[![codecov](https://codecov.io/gh/forestfart/social-twitter-rest/branch/master/graph/badge.svg)](https://codecov.io/gh/forestfart/social-twitter-rest)

This app has been deployed at 

base_url = http://social-twitter-rest.herokuapp.com/twitter

## API documentation

### Create user
User is being created when a first tweet is created with use of URL path

### Posting
To create tweet, simply post body to the URL.

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
User can receive a list of the messages they've posted

HTTP method: 
```bash
GET base_url/'login'
```
i.e. http://social-twitter-rest.herokuapp.com/twitter/JohnMcLane/

You will receive all JohnMcLane tweets in JSON format

### Following
User can follow an another user

HTTP method: 
```bash
GET base_url/'login'/'followLogin'
```
i.e. http://social-twitter-rest.herokuapp.com/twitter/JohnMcLane/LucyAbraham

If JohnMcLane and LucyAbraham have twitted before, you will receive the following message: 
"User JohnMcLane is now following LucyAbraham"

### Timeline
Tweets of all the users followed by certain user

HTTP method:
```bash
GET base_url/'login'/timeline
```
i.e. http://social-twitter-rest.herokuapp.com/twitter/JohnMcLane/timeline

You will receive all tweets of users followed by JohnMcLane in JSON format

### Database content
In the database we have the following users for the testing purposes:

- CoolMan
- JohnMcLane
- LucyAbraham
- tweetUser22
- tweetUserX
- user
- weiredUser
- weiredUserNab

Rules sacved in the database:
- CoolMan follows user
- user follows CoolMan and tweetUserX


#### Feel free to play with it!

Copyright (c) 2018 Michal Michalik
