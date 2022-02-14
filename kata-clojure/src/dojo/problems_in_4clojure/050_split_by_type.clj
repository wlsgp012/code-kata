(ns dojo.problems-in-4clojure.050-split-by-type)
;; https://4clojure.oxal.org/#/problem/50

(defn sol [coll]
  (vals (group-by type coll)))

(= (set (sol [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
(= (set (sol [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
(= (set (sol [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})
