(defproject api-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [environ "1.0.0"]
                 [ring-cors "0.1.7"]
                 [ring-basic-authentication "1.0.5"]
                 [metosin/compojure-api "0.22.0"]]
  :ring {:handler api-test.handler/app}
  :min-lein-version "2.0.0"
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-ring "0.9.6"]]}})
