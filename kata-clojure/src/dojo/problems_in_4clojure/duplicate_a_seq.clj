(ns dojo.problems_in_4clojure.duplicate-a-seq)
; http://www.4clojure.com/problem/32

(defn sol [col] (reduce #(conj %1 %2 %2) [] col))

(println (sol [[1 2] [3 4]]))
(println (sol [1 2 3]))

(println (= (sol [1 2 3]) '(1 1 2 2 3 3)))
(println (= (sol [:a :a :b :b]) '(:a :a :a :a :b :b :b :b)))
(println (= (sol [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))

;other solutions
;mapcat #(list % %)
;#(interleave % %)