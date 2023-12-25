(ns dojo.leetcode.819-most-common-word
  (:require [clojure.string :as s]))

(def paragraph "Bob hit a ball, the hit BALL flew far after it was hit.")
(def banned ["hit"])

(defn get-most-common-word
  [p b]
  (let [filtered (filter #(not (contains? (set b) %)) (s/split (s/replace p #"[^a-zA-Z0-9\s]" "") #" "))]
    (key (first (sort-by val > (frequencies (map s/lower-case filtered)))))))

(comment
  (get-most-common-word paragraph banned))
