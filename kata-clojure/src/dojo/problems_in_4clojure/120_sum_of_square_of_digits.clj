(ns dojo.problems-in-4clojure.120-sum-of-square-of-digits)

(defn sol
  [coll]
  (letfn [(square-of-digits [x]
            (map (comp #(Math/pow % 2) read-string str) (str x)))
          (sum-of-x [xs]
            (reduce + xs))]
    (count (filter true? (map #(> (sum-of-x (square-of-digits %)) %) coll)))))


(= 8 (sol (range 10)))

(= 19 (sol (range 30)))

(= 50 (sol (range 100)))

(= 50 (sol (range 1000)))

;;others
(fn [x] (count
         (for [a x
               :let [b (map read-string (re-seq #"\d" (str a)))]
               :when (> (apply + (map #(* % %) b)) a)
               ] a)))

(fn [coll]
  (let [digits (fn [n] (map #(- (int %) 48) (str n)))
        square #(* % %)
        sum-digits (fn [n] (reduce + (map square (digits n))))]
    (count (filter #(< % (sum-digits %)) coll))))

(fn ssq2 [coll]
  (letfn [(digits [n] (->> n str (map (comp read-string str))))
          (sumsqs [n] (reduce + (map #(* % %) (digits n))))
          (great [n] (< n (sumsqs n))) ]
    (count (filter great coll))))
