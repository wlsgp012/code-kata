(ns dojo.design-pattern.18-factory-method)

(defn maze-builder [maze-fn])

(defn make-wood-maze [])

(defn make-iron-maze [])

(def wood-maze-builder (partial maze-builder make-wood-maze))

(def iron-maze-builder (partial maze-builder make-iron-maze))
