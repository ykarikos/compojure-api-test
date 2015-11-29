(ns api-test.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.middleware.cors :refer [wrap-cors]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :as jetty]
            [compojure.core :refer [ANY]]
            [ring.middleware.basic-authentication :refer [wrap-basic-authentication]]
            [ring.util.http-response :refer :all]))

(def app-port (or (env :port) 3000))

(defn- authenticated?
  [name pass]
  (and (= name (env :api-user))
       (= pass (env :api-passwd))))

(def docs-root "/apidocs")

(defapi api-app
  (ANY (str docs-root "*") []
       (-> (swagger-ui docs-root)
           (wrap-basic-authentication authenticated?)))
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
  (let [origin-url (env :origin-url)
        local-url (or (env :local-url) (str "http://localhost:" app-port))]
    (println "CORS urls:" origin-url local-url)
    (-> api-app
        (wrap-cors :access-control-allow-origin (map re-pattern [origin-url local-url])
                   :access-control-allow-methods [:get :post]))))

(defn -main [& [port-override]]
  (let [port (Integer. (or port-override app-port))]
    (jetty/run-jetty app {:port port :join? false})))
