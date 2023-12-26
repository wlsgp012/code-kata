(ns dojo.leetcode.49-group-anagrams)
;; https://leetcode.com/problems/group-anagrams/

(def strs ["eat","tea","tan","ate","nat","bat"])

(defn to-group [xs]
  (vals (group-by (comp hash-set sort) xs)))

(comment
  (to-group strs))
