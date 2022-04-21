(ns dojo.problems-in-4clojure.135-infix-calculator)
;; https://4clojure.oxal.org/#/problem/135

(defn sol [& xs]
  (let [f (first xs)
        g (partition 2 (rest xs))]
    (reduce (fn [r [op v]] (op r v))
            f
            g)))


(= 7  (sol 2 + 5))

(= 42 (sol 38 + 48 - 2 / 2))

(= 8  (sol 10 / 2 - 1 * 2))

(= 72 (sol 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))

;; others

(fn infix-calc
  [& exp]
  (reduce #(if (fn? %1) (%1 %2) (partial %2 %1)) identity exp))

(fn infix [x op y & more]
  (if more
    (apply infix (op x y) more)
    (op x y)))
