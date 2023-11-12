<script setup>
import Panel from "@/components/Panel.vue";
import {computed, onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {useFetch} from "@/composables/useFetch";
import {useFlash} from "@/composables/useFlash";

const route = useRoute();
const router = useRouter();

const {flash} = useFlash();

const MONTHS = {
  1: "Jan",
  2: "Feb",
  3: "Mar",
  4: "Apr",
  5: "May",
  6: "Jun",
  7: "Jul",
  8: "Aug",
  9: "Sep",
  10: "Oct",
  11: "Nov",
  12: "Dec",
};

let task = ref({});

onMounted(async () => {
  task.value = await useFetch(`http://localhost:8080/api/v1/todos/${route.params.id}`)
      .then((res) => res.json());
})

const datetime = computed(() => {
  const date = new Date(task.value.createdAt);

  let hours = String(date.getHours());
  let minutes = String(date.getMinutes());

  const day = date.getDate();
  const month = MONTHS[date.getMonth() + 1];
  const year = date.getFullYear();

  if (hours.length == 1) hours = `0${hours}`;
  if (minutes.length == 1) minutes = `0${minutes}`;

  return `${hours}:${minutes}, ${day} ${month} ${year}`;
});

const toggleComplete = async () => {
  let body = {
    completed: !!!task.value.completed,
  };

  await useFetch(
      `http://localhost:8080/api/v1/todos/${route.params.id}`,
      "PATCH",
      body,
  );

  router.go();
};

const deleteTask = async () => {
  await useFetch(
      `http://localhost:8080/api/v1/todos/delete/${route.params.id}`,
      "DELETE",
  );

  await flash("Deleted!", "The task has been successfully deleted.");

  router.push("/");
};
</script>

<template>
  <div class="flex-1 container m-auto flex justify-center">
    <div class="max-w-2xl my-auto">
      <RouterLink to="/">
        <p class="text-sm text-center font-mono tracking-wide mb-3">Go Back</p>
      </RouterLink>

      <Panel class="max-w-lg w-80 py-5 px-8">
        <template #heading>
          <div
              class="flex justify-between py-2 border-b border-black items-center mb-4"
          >
            <h2 class="font-bold text-left text-xl overflow-auto">
              {{ task.title }}
            </h2>

            <div class="flex gap-x-4">
              <RouterLink :to="'/task/edit/' + task.id">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                     stroke="currentColor" class="w-5 h-5">
                  <path stroke-linecap="round" stroke-linejoin="round"
                        d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L6.832 19.82a4.5 4.5 0 01-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 011.13-1.897L16.863 4.487zm0 0L19.5 7.125"/>
                </svg>
              </RouterLink>

              <button @click.prevent="deleteTask">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                     stroke="currentColor" class="w-5 h-5">
                  <path stroke-linecap="round" stroke-linejoin="round"
                        d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"/>
                </svg>
              </button>
            </div>
          </div>
        </template>

        <p class="mt-2 break-words">{{ task.text }}</p>
        <template #footer>
          <button
              @click.prevent="toggleComplete"
              class="w-full mt-4 border rounded-lg py-2 font-semibold"
              :class="{
              'bg-green-300': task.completed,
              'bg-gray-300': !task.completed,
            }"
          >
            {{ task.completed ? "Incomplete Task" : "Complete Task" }}
          </button>
          <p class="mt-5 text-xs text-gray-400 text-right">
            Published at {{ datetime }}
          </p>
        </template>
      </Panel>
    </div>
  </div>
</template>
