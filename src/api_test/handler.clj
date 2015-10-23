(ns api-test.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.adapter.jetty :as jetty]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(defapi app
  (swagger-ui "/apidocs")
  (swagger-docs
   {:info {:title "Oma API"
           :version "0.1"
           :description "sitä sun tätä api"}
    :tags [{:name "fi" :description "Finnish language"}
           {:name "se" :description "Swedish language"}]})
  (context* "/api/v1" []
            (context* "/fi" []
                      :tags ["fi"]
                      (GET* "/hello" []
                            :query-params [name :- String]
                            :summary "say hello"
                            (ok {:message (str "Terve, " name)})))
            (context* "/se" []
                     :tags ["se"]
                     (GET* "/hello" []
                           :query-params [name :- String]
                           :summary "say hello"
                           (ok {:message (str "Hejsan, " name)})))))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 5000))]
    (jetty/run-jetty app {:port port :join? false})))
