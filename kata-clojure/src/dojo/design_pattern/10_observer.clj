(ns dojo.design-pattern.10-observer)

;; tracker
(def observers (atom #{}))

(defn add
  [observer]
  (swap! observers conj observer))

(defn notify [user]
  (map #(apply % user) @observers))

;; Fill Observers

(add (fn [u] (println (str "send to fbi : " u))))
(add (fn [u] (println (str "db block user : " u))))

;; User

(def user (atom {}))

(defn add-money
  [user amount]
  (swap! user
         (fn [m] (update-in m [:balance] + amount)))
  (if (> amount 100) (notify)))

(add-watch
 user
 :money-tracker
 (fn [k r os ns]
   (if (< 100 (- (:balance ns) (:balance os)))
     (notify))))
