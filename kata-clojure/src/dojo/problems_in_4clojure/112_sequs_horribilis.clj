(ns dojo.problems-in-4clojure.112-sequs-horribilis)

;;wrong
(defn sol-wrong [n coll]
  (loop [sum 0 xs coll result []]
    (let [es (take-while number? xs)
          next-seq (drop-while number? xs)
          next-sums (reduce #(if (>= n (+ (apply + %1) %2)) (conj %1 %2) %1) [] es)
          next-sum (apply + next-sums)]
      (if  (> n (+ sum next-sum))
        (recur (+ sum next-sum) next-seq (vector (assoc result (count result) (conj (last result) (conj next-sums [])))))
        (seq result)))))

(defn sol [n coll]
  (let [s (tree-seq coll? identity coll)]
    (loop [sum 0 xs s result []]
      (let [es (take-while number? xs)
            next-seq (next (drop-while number? xs))
            current-sum (apply + es)
            next-sum (+ sum current-sum)]
        (if (>= next-sum n)
          ()
          (recur next-sum next-seq (vec (update result (count result) #(conj % )))))))))

(=  (sol 10 [1 2 [3 [4 5] 6] 7])    '(1 2 (3 (4))))

(=  (sol 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])    '(1 2 (3 (4 (5 (6 (7)))))))

(comment (=  (sol 9 (range))    '(0 1 2 3)))

(=  (sol 1 [[[[[1]]]]])    '(((((1))))))

(=  (sol 0 [1 2 [3 [4 5] 6] 7])    '())

(=  (sol 0 [0 0 [0 [0]]])    '(0 0 (0 (0))))

(=  (sol 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
    '(-10 (1 (2 3 (4)))))
