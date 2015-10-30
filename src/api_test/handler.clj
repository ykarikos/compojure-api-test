(ns api-test.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.middleware.cors :refer [wrap-cors]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :as jetty]
            [ring.util.http-response :refer :all]))

(defapi api-app
  (swagger-ui "/apidocs")
  (swagger-docs
   {:info {:title "My very own REST API"
           :version "0.1"
           :description "This API is not very useful as such."}
    :tags [{:name "fi" :description "Finnish language"}
           {:name "se" :description "Swedish language"}]})
  (context* "/api/v1" []
            (context* "/fi" []
                      :tags ["fi"]
                      (GET* "/hello" []
                            :query-params [name :- String]
                            :summary "say hello"
                            (ok {:message (str "Terve, " name)}))
                      (POST* "/test" []
                             :body-params [name :- String]
                             :summary "save hello"
                             (ok {:message (str "hello " name " saved")})))
            (context* "/se" []
                     :tags ["se"]
                     (GET* "/hello" []
                           :query-params [name :- String]
                           :summary "say hello"
                           (ok {:message (str "Hejsan, " name)})))))


(def app
  (let [origin-url (-> env :origin-url re-pattern)]
    (wrap-cors api-app
               :access-control-allow-origin [origin-url]
               :access-control-allow-methods [:get :post])))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty app {:port port :join? false})))
