(ns dojo.game-of-life.short)

(defn neighbours [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1]
        :when (not= 0 dx dy)]
    [(+ dx x) (+ dy y)]))

(defn hex-neighbours [[x y]]
  (for [dx [-1 0 1] dy (if (zero? dx) [-2 2] [-1 1])]
    [(+ dx x) (+ dy y)]))

;; simple version
(defn step [cells]
  (set (for [[loc n] (frequencies (mapcat neighbours cells))
             :when (or (= n 3) (and (= n 2) (cells loc)))]
         loc)))

(defn populate [board living-cells]
  (reduce (fn [board cooridnates]
            (assoc-in board cooridnates :on))
          board
          living-cells))

;; complcated version
(defn count-neighbours [board loc]
  (count (filter #(get-in board %) (neighbours loc))))

(defn step2 [board]
  (let [w (count board)
        h (count (first (board)))]
    (loop [new-board board x 0 y 0]
      (cond
        (>= x w) new-board
        (>= y h) (recur new-board (inc x) 0)
        :else
        (let [new-liveness
              (case (count-neighbours board [x y])
                2 (get-in board [x y])
                3 :on
                nil)]
          (recur (assoc-in new-board [x y] new-liveness) x (inc y)))))))
