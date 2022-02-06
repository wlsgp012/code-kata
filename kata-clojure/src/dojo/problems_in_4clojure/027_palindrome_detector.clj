(ns dojo.problems-in-4clojure.027-palindrome-detector)
;; https://4clojure.oxal.org/#/problem/27

(defn answer
  [sq]
  (= sq (reverse sq)))

(false? (answer '(1 2 3 4 5)))
(true? (answer "racecar"))
(true? (answer [:foo :bar :foo]))
(true? (answer '(1 1 3 3 1 1)))
(false? (answer '(:a :b :c)))

;; others

#(= (vec %) (reverse %))
