<script setup>
import { computed } from "vue";

const props = defineProps(["task"]);

const kebabizedTitle = computed(() => {
  return props.task.title.toLowerCase().replaceAll(" ", "-");
});
</script>

<template>
  <li class="flex justify-between items-center py-3 px-4">
    <label
      :for="kebabizedTitle"
      class="w-1/2 underline-offset-4 decoration-1 hover:underline break-words"
    >
      <RouterLink :to="'/task/' + task.id">
        {{ task.title }}
      </RouterLink>
    </label>

    <span
      class="text-sm font-mono font-semibold tracking-wide"
      :class="task.completed ? 'text-green-400' : 'text-gray-600'"
    >
      {{ task.completed ? "completed" : "in-progress" }}
    </span>
  </li>
</template>
