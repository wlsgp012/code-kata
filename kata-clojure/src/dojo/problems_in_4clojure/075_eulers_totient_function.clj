(ns dojo.problems-in-4clojure.075-eulers-totient-function)
;; https://4clojure.oxal.org/#/problem/75

(defn sol [x]
  (letfn [(gcd [x y]
            (if (zero? x)
              y
              (gcd (mod y x) x)))]
    (count (filter #(= 1 (gcd x %)) (take x (rest (range)))))))

(= (sol 1) 1)
(= (sol 10) (count '(1 3 7 9)) 4)
(= (sol 40) 16)
(= (sol 99) 60)
