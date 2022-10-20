(ns dojo.problems-in-4clojure.114-global-take-while)

;; not lazy
(defn sol2 [n p s]
  (loop [c n [x & xs] s r []]
    (if (zero? c)
      (drop-last r)
      (let [b (p x)]
        (recur (if b (dec c) c) xs (conj r x))))))

(defn sol [n p [x & xs]]
  (let [c (if (p x) (dec n) n)]
    (when (not (zero? c))
      (cons x (sol c p xs)))))

(= [2 3 5 7 11 13]
   (sol 4 #(= 2 (mod % 3))
       [2 3 5 7 11 13 17 19 23]))

(= ["this" "is" "a" "sentence"]
   (sol 3 #(some #{\i} %)
       ["this" "is" "a" "sentence" "i" "wrote"]))

(= ["this" "is"]
   (sol 1 #{"a"}
        ["this" "is" "a" "sentence" "i" "wrote"]))

;; others
(fn take-n-while [i pred coll]
  (let [[f [m & r]] (split-with (complement pred) coll)]
    (if (= 1 i)
      f
      (concat f [m] (take-n-while (dec i) pred r)))))

(fn [n prd col]
  (let [a (atom 0)]
    (take-while
     (fn [x] (if (prd x) (swap! a inc)) (< @a n)) col)))
