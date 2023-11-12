<script setup>
import {computed, onMounted, ref} from "vue";
import Panel from "@/components/Panel.vue";
import TodoListItem from "@/components/TodoListItem.vue";
import { useFetch } from "@/composables/useFetch";

let tasks = ref([]);

onMounted(async () => {
  tasks.value = await useFetch("http://localhost:8080/api/v1/todos")
                        .then((res) => res.json());
});

const sortedTasks = computed(() => {
  return tasks.value.toSorted((a, b) => a.completed - b.completed);
});
</script>

<template>
  <div class="flex-1 container m-auto flex justify-center">
    <div class="max-w-2xl my-auto">
      <Panel class="w-80" v-if="tasks.length">
        <template #heading>
          <div class="flex justify-between items-start mb-4">
            <h3 class="font-bold text-left text-lg capitalize">
              Tasks <span class="opacity-50"> ({{ sortedTasks.length }}) </span>
            </h3>
            <RouterLink to="/task/create" class="text-xl">&plus;</RouterLink>
          </div>
        </template>

        <ul class="border border-black divide-y divide-black mt-3">
          <TodoListItem v-for="task in sortedTasks" :key="task.id" :task="task">
            {{ task.title }}
          </TodoListItem>
        </ul>
      </Panel>
      <div v-else class="text-center font-semibold tracking-wide">
        <h1 class="text-6xl">You have no tasks.</h1>
        <h3 class="mt-7 text-2xl animate-bounce">
          <RouterLink to="/task/create">
            Click me and create a new one!
          </RouterLink>
        </h3>
      </div>
    </div>
  </div>
</template>
