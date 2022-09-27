(ns dojo.problems-in-4clojure.093-partially-flatten-a-sequence)

(def sol
  (fn [xs]
    (filter (fn [x] (and (sequential? x) (every? (complement sequential?) x)))
     (tree-seq sequential? identity xs))))

(= (sol [["Do"] ["Nothing"]])
   [["Do"] ["Nothing"]])

(= (sol [[[[:a :b]]] [[:c :d]] [:e :f]])
   [[:a :b] [:c :d] [:e :f]])

(= (sol '((1 2)((3 4)((((5 6)))))))
   '((1 2)(3 4)(5 6)))

;; others
(fn pflatten [tree]
  (if (every? sequential? tree)
    (mapcat pflatten tree)
    [tree]))
