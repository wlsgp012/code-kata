(ns dojo.problems-in-4clojure.115-the-balance-of-n)

(defn sol [n]
  (loop [l 0 s (map #(Integer/parseInt %) (re-seq #"\d" (str n))) r 0]
    (if (or (empty? s) (= 1 (count s)))
      (= r l)
      (recur (+ l (first s)) (drop-last (next s)) (+ r (last s))))))

(= true (sol 11))
(= true (sol 121))
(= false (sol 123))
(= true (sol 0))
(= false (sol 88099))
(= true (sol 89098))
(= true (sol 89089))
(= (take 20 (filter sol (range)))
   [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])

;; others
(fn balanced? [n]
  (letfn [(take-half [s] (take (quot (count s) 2) s))]
    (= (apply + (map #(Integer/parseInt (str %)) (take-half (str n))))
       (apply + (map #(Integer/parseInt (str %)) (take-half (reverse (str n))))))))


(fn b [n]
  (let [v (map #(read-string (str %)) (str n))
        m (quot (count v) 2)]
    (= (apply + (take m v)) (apply + (take-last m v)))))
