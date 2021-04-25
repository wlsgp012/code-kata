;p.73

(defn sum-of-list [list]
  (if (empty? list)
    0
    (+
      (first list) 
      (sum-of-list (rest list)))))

(sum-of-list '(1 2 3 4))

(defn choose-bigger [x y]
 (if (> x y)
   x
   y))

(defn max-of-list [list]
  (cond
    (empty? list) 0
    (= (count list) 1) (first list)
    "default" (choose-bigger (first list) (max-of-list (rest list)))))

(max-of-list '(4 3 2 5 1))

(defn my-map [fp list]
 (if (empty? list) 
   nil
   (cons 
     (fp (first list))
     (my-map fp (rest list)))))
(my-map (fn [x] (+ x 1)) '(1 2 3))

