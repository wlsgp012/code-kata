(ns dojo.problems-in-4clojure.114-global-take-while)

(defn sol [n p s]
  )

(= [2 3 5 7 11 13]
   (sol 4 #(= 2 (mod % 3))
       [2 3 5 7 11 13 17 19 23]))

(= ["this" "is" "a" "sentence"]
   (sol 3 #(some #{\i} %)
       ["this" "is" "a" "sentence" "i" "wrote"]))

(= ["this" "is"]
   (sol 1 #{"a"}
       ["this" "is" "a" "sentence" "i" "wrote"]))
