# api-test

Tryin out [compojure-api](https://github.com/metosin/compojure-api). Try it out at http://compojure-api-test.herokuapp.com/apidocs/

## Usage

### Run the application locally

`lein ring server`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -cp target/server.jar clojure.main -m api-test.handler
```

### Packaging as war

`lein ring uberwar`

## License

MIT
