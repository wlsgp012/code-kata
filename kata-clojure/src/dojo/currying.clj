(ns dojo.currying)

(defn ab [a b]
  (str a b))

(defn manual-currying [ab]
  (fn [x] (fn [y] (ab x y))))

(defn flip [f]
  (fn [x] (fn [y] ((f y) x))))
