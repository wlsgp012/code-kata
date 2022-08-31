(ns kata.alice-wonderland.magic-square.puzzle
  (:require [clojure.set :as s]))

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn- init [mi ma me]
  [[0 mi 0]
   [0 me 0]
   [0 ma 0]])

(defn- pair
  [subs]
  (distinct
   (filter (fn [[a b]] (not= a b))
           (mapcat (fn [x] (map #(sort (vector x %)) subs)) subs))))

(defn- get-two-numbers
  [goal subs & targets]
  (let [s (- goal (reduce + targets))]
    (first (filter (fn [[a b]] (= s (+ a b))) (pair subs)))))

(defn- get-subs
  [square values]
  (s/difference (set values) (set (flatten square))))

(defn- fill-first
  [square values goal]
  (let [subs (get-subs square values)
        two (apply get-two-numbers goal subs (first square))]
    (assoc square 0 [(first two) (second (first square)) (last two)])))

(defn- fill-3-3
  [square goal]
  (let [a (ffirst square)
        b (second (second square))
        result (- goal a b)
        third-row (last square)]
    (assoc square 2 (conj (vec (drop-last third-row)) result))))

(defn- fill-last
  [square goal]
  (let [[_ a b] (last square)
        result (- goal a b)]
    (assoc-in square [2 0] result)))

(defn- fill-2-1
  [square goal]
  (let [a (ffirst square)
        b (first (last square))
        result (- goal a b)]
    (assoc-in square [1 0] result)))

(defn- fill-second
  [squre goal]
  (let [[a b] (second squre)
        result (- goal a b)]
    (assoc-in squre [1 2] result)))

(defn magic-square [values]
  (let [mi (apply min values)
        ma (apply max values)
        me (nth values (quot (count values) 2))
        goal (+ mi ma me)]
    (-> (init mi ma me)
        (fill-first values goal)
        (fill-3-3 goal)
        (fill-last goal)
        (fill-2-1 goal)
        (fill-second goal))))

(comment
  (magic-square values))
