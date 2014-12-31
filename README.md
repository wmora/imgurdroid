# Imgurdroid
Basic Imgur client for Android. Serves as a sample app for an image gallery fetched from a server.

<img src="art/screenshot_1.png" width=180>

## Dependencies
Imgurdroid uses Imgur's [API](https://api.imgur.com/) to fetch information.

You'll need to sign up as a developer and add the client ID and secret to your local
`~/.gradle/gradle.properties` as the following:

```
IMGURDROID_CLIENT_ID=your_client_id
IMGURDROID_CLIENT_SECRET=your_client_secret
```

## Libraries
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Picasso](http://square.github.io/picasso/)
* [SlidingTabs](https://github.com/nispok/slidingtabs)
* [TinyBus](https://github.com/beworker/tinybus)