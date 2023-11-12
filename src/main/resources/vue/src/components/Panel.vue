<template>
  <div
    class="flex flex-col justify-between border rounded-md border-gray-900 p-6"
  >
    <slot name="heading" />

    <slot />

    <slot name="footer" />
  </div>
</template>
