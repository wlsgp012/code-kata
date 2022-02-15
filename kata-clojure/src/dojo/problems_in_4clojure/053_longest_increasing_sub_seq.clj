(ns dojo.problems-in-4clojure.053-longest-increasing-sub-seq)
;; https://4clojure.oxal.org/#/problem/53

(defn grouping [coll]
  (filter #(every? some? %)
          (partition-by nil? (mapv #(if (= (inc %1) %2) [%1 %2] nil) (butlast coll) (rest coll)))))

(defn sol
  [coll]
  (let [groups (grouping coll)]
    (if (empty? groups)
      []
      (distinct
       (flatten
        (apply max-key count groups))))))

(= (sol [1 0 1 2 3 0 4 5]) [0 1 2 3])
(= (sol [5 6 1 3 2 7]) [5 6])
(= (sol [2 3 3 4 5]) [3 4 5])
(= (sol [7 6 5 4]) [])

;; others

(fn [coll]
  (->> (partition 2 1 coll)
       (partition-by #(- (second %) (first %)))
       (filter #(= 1 (- (second (first %)) (ffirst %))))
       (reduce #(if (< (count %1) (count %2)) %2 %1) [])
       (flatten)
       (distinct)))
