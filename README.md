# week3project

## Problem Domain
The project domain that our group is planning on focusing on is media management. Our group plans on making a music 
recommendation and management tool for Spotify users.

Spotify has an API that allows you to check out a user's profile, playlists, saved tracks, followed artists, 
and many other features. It also allows you to manage their account such as adding new items to their playlists and
controlling what they are currently listening to.

We plan on using this tool to create an application that functions as a media manager. Users will be able to control
their spotify accounts as well as get new recommendations based on their profile as well as be able to check out their
friend's profiles and what music their primarily listen to. We are planning on using the user's information and use
known algorithms to find similar songs, artists and genres (currently we are considering cosine similarity).

## API Documentation
https://developer.spotify.com/documentation/web-api

## API Usage
We used Postman to test out the API. We first created a token for us to use and then used the token to access things
like artists and songs. The screenshots of our progress is below: 

![Postman Example 1.png](src%2Fimages%2FPostman%20Example%201.png)
![Postman Example 2.png](src%2Fimages%2FPostman%20Example%202.png)
![Postman Example 3.png](src%2Fimages%2FPostman%20Example%203.png)

## Java Code
We created an example of an API call with java, creating an Artist object.

The example shown below is when we call the information for the artist "Pitbull".
You can find our code in the src folder of this repository.

![Java Output Example.png](src%2Fimages%2FJava%20Output%20Example.png)

## Technical Problems
- We are a bit unsure of how to create the recommendation tool. How are we going to implement the cosine similarity
(or any other tool for finding similar objects) into Java?
- We have to create a new token every hour. How can we implement this into the program so that the token is created 
automatically for us?