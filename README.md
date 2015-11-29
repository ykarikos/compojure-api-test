# Compojure API test

Trying out [compojure-api](https://github.com/metosin/compojure-api) with
- [Heroku](https://www.heroku.com/),
- [ring-cors](https://github.com/r0man/ring-cors) and
- [ring-basic-authentication](https://github.com/remvee/ring-basic-authentication).

The REST API docs: https://compojure-api-test.herokuapp.com/apidocs/ (username *foo*, password *bar*).

Try posting from another host: https://ykarikos.github.io/compojure-api-test/

## Usage

### Run the application locally

```
export API_USER=foo
export API_PASSWD=bar
export ORIGIN_URL=http://localhost:8080
lein ring server
```

The apidocs are now running at [http://localhost:3000/apidocs/](http://localhost:3000/apidocs/).

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -cp target/server.jar clojure.main -m api-test.handler
```

## License

Compojure-api-test (C) 2015 Yrjö Kari-Koskinen ykk@peruna.fi

Compojure api test's source code is licensed with the MIT License, see [LICENSE.txt](https://github.com/ykarikos/compojure-api-test/blob/master/LICENSE.txt).

# Thanks

This project is a grateful recipient of the [Futurice Open Source sponsorship program](http://futurice.com/blog/sponsoring-free-time-open-source-activities?utm_source=github&utm_medium=spice).
