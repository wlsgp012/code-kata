(ns dojo.problems-in-4clojure.044-rotate-sequence)
;; https://4clojure.oxal.org/#/problem/44

(defn sol
  [i coll]
  (loop [c i result coll]
    (cond
      (zero? c) result
      (pos? c) (recur (dec c) (vec (conj (vec (rest result)) (first result))))
      :else (recur (inc c) (vec (conj (butlast result) (last result)))))))

(defn sol2
  [i coll]
  (let [p (rem i (count coll))]
    (if (pos? p)
      (concat (drop  p coll) (take p coll))
      (concat (take-last (Math/abs p) coll) (drop-last (Math/abs p) coll)))))

(= (sol 2 [1 2 3 4 5]) '(3 4 5 1 2))
(= (sol -2 [1 2 3 4 5]) '(4 5 1 2 3))
(= (sol 6 [1 2 3 4 5]) '(2 3 4 5 1))
(= (sol 1 '(:a :b :c)) '(:b :c :a))
(= (sol -4 '(:a :b :c)) '(:c :a :b))

;; others
#(take (count %2)
       (drop (mod % (count %2))
             (cycle %2)))
