(ns dojo.problems-in-4clojure.073-analyze-a-tic-tac-toe-board)
;; https://4clojure.oxal.org/#/problem/73

(defn sol [board]
  (let [diagonal (fn d [pos incdec coll] (when (seq coll)
                                           (cons (get (first coll) pos) (d (incdec pos) incdec (rest coll)))))
        bingo? (fn [coll] (every? #(and (not= :e %) (= (first coll) %)) coll))
        lotated (partition 3 (apply interleave board))
        added (into board lotated)
        target (conj added (diagonal 0 inc board) (diagonal 2 dec board))]
    (ffirst (filter bingo? target))))

(= nil (sol [[:e :e :e]
            [:e :e :e]
            [:e :e :e]]))
(= :x (sol [[:x :e :o]
           [:x :e :e]
           [:x :e :o]]))
(= :o (sol [[:e :x :e]
           [:o :o :o]
           [:x :e :x]]))
(= nil (sol [[:x :e :o]
            [:x :x :e]
            [:o :x :o]]))
(= :x (sol [[:x :e :e]
           [:o :x :e]
           [:o :e :x]]))
(= :o (sol [[:x :e :o]
           [:x :o :e]
           [:o :e :x]]))
(= nil (sol [[:x :o :x]
            [:x :o :x]
             [:o :x :o]]))

;; others
(fn [a x b]
  (ffirst
   (remove #(or (some #{:e} %) (a not= %))
           `(~@b ~@(a map list b) ~(x b [0 1 2]) ~(x b [2 1 0])))))
;; apply #(map get % %2)
