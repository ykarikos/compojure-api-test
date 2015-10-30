# Compojure API test

Trying out [compojure-api](https://github.com/metosin/compojure-api).

The REST API docs: http://compojure-api-test.herokuapp.com/apidocs/

Try posting from another host: http://ykarikos.github.io/compojure-api-test/

## Usage

### Run the application locally

```
export ORIGIN_URL=http://localhost:8080
lein ring server
```

The apidocs are running at http://localhost:5000/apidocs/

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -cp target/server.jar clojure.main -m api-test.handler
```

## License

Compojure-api-test (C) 2015 Yrjö Kari-Koskinen ykk@peruna.fi

Compojure api test's source code is licensed with the MIT License, see [LICENSE.txt](https://github.com/ykarikos/compojure-api-test/blob/master/LICENSE.txt).

