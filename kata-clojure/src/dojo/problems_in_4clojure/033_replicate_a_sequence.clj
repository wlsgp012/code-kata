(ns dojo.problems-in-4clojure.033-replicate-a-sequence)
; http://www.4clojure.com/problem/33

(defn sol [coll n] (mapcat #(repeat n %) coll))

(println (sol [1 2 3] 2))

(println (= (sol [1 2 3] 2) '(1 1 2 2 3 3)))
(println (= (sol [:a :b] 4) '(:a :a :a :a :b :b :b :b)))

; other solution
; (fn [x t] (mapcat (partial repeat t) x))