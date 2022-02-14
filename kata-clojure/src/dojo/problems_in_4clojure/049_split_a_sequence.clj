(ns dojo.problems-in-4clojure.049-split-a-sequence)
;; https://4clojure.oxal.org/#/problem/49

(defn sol
  [n coll]
  (concat [(take n coll)] [(drop n coll)]))

(= (sol 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
(= (sol 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(= (sol 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])

;; others
(fn [n xs] [(take n xs) (drop n xs)])

(juxt take drop)
