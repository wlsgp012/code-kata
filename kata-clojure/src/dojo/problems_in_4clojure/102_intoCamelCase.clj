(ns dojo.problems-in-4clojure.102-intoCamelCase)

(def sol
  (fn [word]
    (let [separated (re-seq #"\w+" word)]
      (clojure.string/join (cons (first separated) (map clojure.string/capitalize (rest separated)))))))

(= (sol "something") "something")

(= (sol "multi-word-key") "multiWordKey")

(= (sol "leaveMeAlone") "leaveMeAlone")

;; others
(fn [str] (clojure.string/replace str #"-\w" #(-> % (.substring 1 2) .toUpperCase)))

(fn [s]
  (let [[h & t] (clojure.string/split s #"\-")]
    (clojure.string/join (cons h (map clojure.string/capitalize t)))))
