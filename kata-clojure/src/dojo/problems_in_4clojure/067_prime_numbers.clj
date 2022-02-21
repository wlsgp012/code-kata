(ns dojo.problems-in-4clojure.067-prime-numbers)
;; https://4clojure.oxal.org/#/problem/67

(defn prime?
  [z]
  (= 2 (count (set (filter #(int? (/ z %)) (take z (rest (range))))))))

(defn sol
  [n]
  (take n (filter prime? (nnext (range)))))

(= (sol 2) [2 3])
(= (sol 5) [2 3 5 7 11])
(= (last (sol 100)) 541)

;; others
(fn [x]
  (take x
        (remove
         (fn [n]
           (some #(= 0 (mod n %)) (range 2 (inc (int (Math/sqrt n))))))
         (iterate inc 2))))

(fn[n] (take n (filter #(not-any? (fn[x] (= 0 (rem % x))) (range 2 %)) (iterate inc 2))))

(fn primes [n]
  (letfn [(prime? [n]
            (every? #(not= 0 (mod n %)) (range 2 n)))]
    (take n (filter prime? (iterate inc 2)))))


(fn [x]
  (take x
        (drop 2
              (filter
               (fn [n]
                 (not (some #(= 0 (mod n %)) (range 2 n))))
               (range)))))
