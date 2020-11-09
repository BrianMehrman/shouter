(ns shouter.controllers.shouts
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [shouter.views.shouts :as view]
            [shouter.models.shout :as model]
            [taoensso.carmine :as car :refer (wcar)]))

(def server1-conn {:pool {} :spec {:host "127.0.0.1" :port 6379}})
(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

(defn index []
  (view/index (model/all)))

(defn get-id [shout]
  (get (first shout) :id))

(defn notify->create [shout]
  (wcar* (car/set (str "shout:" (get-id shout) ":created") "created")))

(defn create
  [shout]
  (when-not (str/blank? shout)
    (->>
     (model/create shout)
     (notify->create)))
  (ring/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (POST "/" [shout] (create shout)))

(comment
  (wcar* (mapv #(car/set (str "key-" %) (rand-int 10)) (range 3))
         (mapv #(car/get (str "key-" %)) (range 3))))
(comment
  (mapv #(vec [(str "key-" %) (rand-int 10)]) (range 3))
  ;[["key-0" 5] ["key-1" 5] ["key-2" 3]]
  )