(ns dojo.problems-in-4clojure.070-word-sorting
  (:require [clojure.string :as s]))
;; https://4clojure.oxal.org/#/problem/70

(defn sol
  [x]
  (sort-by (partial s/upper-case) (s/split (s/replace x #"[!\.]" "") #" ")))


(= (sol  "Have a nice day.")
   ["a" "day" "Have" "nice"])
(= (sol  "Clojure is a fun language!")
   ["a" "Clojure" "fun" "is" "language"])
(= (sol  "Fools fall for foolish follies.")
   ["fall" "follies" "foolish" "Fools" "for"])

;; others
(fn [sentence] (sort-by #(.toLowerCase %) (re-seq #"\w+" sentence)))
