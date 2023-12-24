(ns dojo.leetcode.937-reorder-data-in-log-files
  (:require [clojure.string :as s]))
;; https://leetcode.com/problems/reorder-data-in-log-files/

(def logs ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"])

(defn reorder [xs]
  (let [parsed (fn [x] (s/split x #" "))
        digit? (fn [x] (some? (re-matches #"[0-9]+" (second x))))
        grouped (group-by #(digit? (parsed %)) xs)]
    (concat
     (sort-by (juxt #(s/join (rest (parsed %))) #(first (parsed %))) (grouped false))
     (grouped true))))
