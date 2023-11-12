import { createRouter, createWebHistory } from "vue-router";
import TodoList from "@/views/TodoList.vue";
import TodoItem from "@/views/TodoItem.vue";
import TodoItemEdit from "@/views/TodoItemEdit.vue";
import TodoItemCreate from "@/views/TodoItemCreate.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "index",
      component: TodoList,
    },
    {
      path: "/task/:id",
      name: "show",
      component: TodoItem,
    },
    {
      path: "/task/edit/:id",
      name: "edit",
      component: TodoItemEdit,
    },
    {
      path: "/task/create",
      name: "create",
      component: TodoItemCreate,
    },
  ],
});

export default router;
