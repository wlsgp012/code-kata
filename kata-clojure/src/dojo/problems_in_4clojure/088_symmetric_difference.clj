(ns dojo.problems-in-4clojure.088-symmetric-difference)
;; https://4clojure.oxal.org/#/problem/88

(defn sol
  [a b]
  (clojure.set/union (clojure.set/difference a b) (clojure.set/difference b a)))

(= (sol #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})

(= (sol #{:a :b :c} #{}) #{:a :b :c})

(= (sol #{} #{4 5 6}) #{4 5 6})

(= (sol #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})

;; others

(fn [x y]
  (into (apply disj x y) (apply disj y x)))

(fn [a b]
  (reduce #((if (% %2) disj conj) % %2) a b))
