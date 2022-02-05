(ns dojo.problems-in-4clojure.041-drop-every-nth-item)
; http://www.4clojure.com/problem/41

(defn sol [col n] (flatten (partition-all (- n 1) n col)))

(println (= (sol [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8]))
(println (= (sol [:a :b :c :d :e :f] 2) [:a :c :e]))
(println (= (sol [1 2 3 4 5 6] 4) [1 2 3 5 6]))

;other solution
;#(apply concat (partition-all (dec %2) %2 %))