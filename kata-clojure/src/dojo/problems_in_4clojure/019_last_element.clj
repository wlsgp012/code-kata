(ns dojo.problems-in-4clojure.019-last-element)
;; https://4clojure.oxal.org/#/problem/19

(defn answer
  [col]
  (first (reverse col)))

(= (answer [1 2 3 4 5]) 5)
(= (answer '(5 4 3)) 3)
(= (answer ["b" "c" "d"]) "d")

;; other solution
(fn [[x & xs]]
  (if (seq xs)
    (recur xs)
    x))

(comp first reverse)

#(nth % (- (count %) 1))

(comp peek vec)