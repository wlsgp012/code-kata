(ns dojo.problems-in-4clojure.085-power-set)
;; https://4clojure.oxal.org/#/problem/85

(defn sol
  [s]
  )

(= (sol #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})

(= (sol #{}) #{#{}})

(= (sol #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})

(= (count (sol (into #{} (range 10)))) 1024)
