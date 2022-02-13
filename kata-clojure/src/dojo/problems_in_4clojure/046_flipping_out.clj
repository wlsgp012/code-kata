(ns dojo.problems-in-4clojure.046-flipping-out)
;; https://4clojure.oxal.org/#/problem/46

(defn sol [f]
  (fn [& arg] (apply f (reverse arg))))

(= 3 ((sol nth) 2 [1 2 3 4 5]))
(= true ((sol >) 7 8))
(= 4 ((sol quot) 2 8))
(= [1 2 3] ((sol take) [1 2 3 4 5] 3))
