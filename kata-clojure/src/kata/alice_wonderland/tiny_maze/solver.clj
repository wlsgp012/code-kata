(ns kata.alice-wonderland.tiny-maze.solver)

(defn- find-ways
  [maze pos]
  (let [[x y] pos
        max-x (dec (count (first maze)))
        max-y (dec (count maze))
        no-way [:out :out]
        to-x (if (= x max-x) no-way [(inc x) y])
        to-y (if (= y max-y) no-way [x (inc y)])]
    (filter (fn [to] (let [next (get-in maze to)] (and (some? next) (not= 1 next)))) [to-x to-y])))

(defn- mark-trace [maze trace]
  (reduce (fn [updated-maze pos] (assoc-in updated-maze pos :x)) maze trace))

(defn solve-maze
  ([maze] (solve-maze maze [0 0] []))
  ([maze pos trace]
   (case (get-in maze pos)
     :E (mark-trace maze (conj trace pos))
     (first (filter seq (map (fn [way] (solve-maze maze way (conj trace pos))) (find-ways maze pos)))))))

(def m [[:S 0 0 1]
        [1  1 0 0]
        [1  0  0 1]
        [1  1  0 :E]])

(comment
  (solve-maze m))
